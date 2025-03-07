package com.example.dto.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryFromGithub {
    public String name;
    public Owner owner;
    public boolean fork;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        public String login;
    }
}
