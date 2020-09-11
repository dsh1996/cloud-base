package com.cloud.easytoolsserver.model;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GenneratorConfig {
    private GlobalConfig globalConfig;
    private StrategyConfig strategyConfig;
    private DataSourceConfig dataSourceConfig;
    private PackageConfig packageConfig;
}
