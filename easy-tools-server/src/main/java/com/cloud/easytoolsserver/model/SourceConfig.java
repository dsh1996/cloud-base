package com.cloud.easytoolsserver.model;

import lombok.Data;

@Data
public class SourceConfig {
    private String url;
    private String driverName;
    private String username;
    private String password;

}
