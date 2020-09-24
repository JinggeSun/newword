package com.item.mydoc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author zcm
 */
@SpringBootApplication
@RestController
public class MydocApplication {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(MydocApplication.class, args);
	}

	@GetMapping("/db")
	public String createDb(){

		DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);

		// 生成文件配置
		EngineConfig engineConfig = EngineConfig.builder()
				// 生成文件路径，自己mac本地的地址，这里需要自己更换下路径
				.fileOutputDir("./")
				// 打开目录
				.openOutputDir(false)
				// 文件类型
				.fileType(EngineFileType.WORD)
				// 生成模板实现
				.produceType(EngineTemplateType.freemarker).build();

		// 生成文档配置（包含以下自定义版本号、描述等配置连接）
		Configuration config = Configuration.builder()
				.version("1.0.3")
				.description("生成文档信息描述")
				.dataSource(dataSourceMysql)
				.engineConfig(engineConfig)
				//.produceConfig(getProcessConfig())
				.build();

		// 执行生成
		new DocumentationExecute(config).execute();


		return "success";
	}
}
