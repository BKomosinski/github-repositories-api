package com.example.config;

import com.example.dto.github.BranchFromGithub;
import com.example.dto.github.RepositoryFromGithub;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "github-api")
// 🔐 Dodajemy Authorization nagłówek
@ClientHeaderParam(name = "Authorization", value = "token ${rest-client.github-api.token}")
public interface GithubApiConfig {

    @GET
    @Path("/users/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<RepositoryFromGithub>> getRepositories(@PathParam("username") String username);

    @GET
    @Path("/repos/{username}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<BranchFromGithub>> getBranches(@PathParam("username") String username, @PathParam("repo") String repo);
}