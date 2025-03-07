package com.example.controller;

import com.example.dto.RepositoryResponse;
import com.example.service.GithubService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/repositories")
public class RepositoryController {

    @Inject
    GithubService githubService;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<RepositoryResponse>> getRepositories(@PathParam("username") String username) {
        return githubService.getUserRepositories(username);
    }
}
