package com.example.service;

import com.example.config.GithubApiConfig;
import com.example.dto.BranchDto;
import com.example.dto.RepositoryResponse;
import com.example.exception.UserNotFoundException;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;

@ApplicationScoped
public class GithubService {

    @RestClient
    GithubApiConfig githubApi;

    public Uni<List<RepositoryResponse>> getUserRepositories(String username) {
        return githubApi.getRepositories(username)
                .onFailure(WebApplicationException.class)
                .transform(ex -> {
                    if (((WebApplicationException)ex).getResponse().getStatus() == 404)
                        return new UserNotFoundException(username);
                    return ex;
                })
                .onItem().transformToMulti(Multi.createFrom()::iterable)
                .filter(repo -> !repo.fork)
                .onItem().transformToUniAndMerge(repo ->
                        githubApi.getBranches(username, repo.name)
                                .map(branches -> new RepositoryResponse(
                                        repo.name,
                                        repo.owner.login,
                                        branches.stream()
                                                .map(b -> new BranchDto(b.name, b.commit.sha))
                                                .toList()
                                ))
                )
                .collect().asList();
    }
}
