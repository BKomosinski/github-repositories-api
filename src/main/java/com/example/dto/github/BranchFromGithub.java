package com.example.dto.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchFromGithub {
    public String name;
    public Commit commit;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        public String sha;
    }
}
