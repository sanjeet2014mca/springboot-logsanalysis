

create database nxp_database;
use nxp_database;

CREATE TABLE `nxp_pipelines` (
	`nxp_pipelines_id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`nxp_pipelines_id`)
);

INSERT INTO `nxp_pipelines` (`nxp_pipelines_id`, `name`) VALUES ('100', 'Jenkins Pipeline Result');
INSERT INTO `nxp_pipelines` (`nxp_pipelines_id`, `name`) VALUES ('101', 'Syntax Check');
INSERT INTO `nxp_pipelines` (`nxp_pipelines_id`, `name`) VALUES ('102', 'Analysis');
INSERT INTO `nxp_pipelines` (`nxp_pipelines_id`, `name`) VALUES ('103', 'Post Deployment');

CREATE TABLE `nxp_pipelines_logs` (
	`nxp_pipelines_logs_id` INT NOT NULL AUTO_INCREMENT,
	`nxp_pipelines_id` INT NOT NULL DEFAULT 0,
	`headers_name` VARCHAR(200) NOT NULL,
	`headers_display` VARCHAR(200) NOT NULL,
	`headers_value` VARCHAR(200) NULL,
	`durations` SMALLINT NOT NULL DEFAULT 0,
	`logs` LONGTEXT NULL DEFAULT NULL,
	PRIMARY KEY (`nxp_pipelines_logs_id`),
	CONSTRAINT `FK__nxp_pipelines` FOREIGN KEY (`nxp_pipelines_id`) REFERENCES `nxp_pipelines` (`nxp_pipelines_id`)  ON UPDATE RESTRICT ON DELETE RESTRICT
);


INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) VALUES ('111', '100', 'syntax_check', 'Syntax Check', 'Successs', '2', '{
	"syntax_check": "Successs"
}');

INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) VALUES ('112', '100', 'static_code_analysis', 'Static Code Analysis', 'Failed', '3', '{
	"static_code_analysis": "Failed"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) VALUES ('113', '100', 'pre_deployment_result', 'Predeployment Result', 'Successs', '1', '{
	"pre_deployment_result": "Successs"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) VALUES ('114', '100', 'provisioning_result', 'Provisioning Result', 'Failed', '8', '{
	"provisioning_result": "Failed"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) VALUES ('115', '100', 'JenkinsLogOutputPath', 'JenkinsLogOutputPath', 'C:\\Users\\Manish\\Downloads\\pipelineResult.json', '20', '{
	"provisioning_result": "Failed",
	"static_code_analysis": "Failed",
	"pre_deployment_result": "Successs",
	"syntax_check": "Successs",
	"JenkinsLogOutputPath": "C:\\Users\\Manish\\Downloads\\pipelineResult.json"
}');

INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('116', '101', 'syntax_check', 'Syntax Check', 'Failed', '21', '{
	"syntax_check": "Failed"
}');

INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('117', '101', 'static_code_analysis', 'Static Code Analysis', 'Failed', '4', '{
	"static_code_analysis": "Failed"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('118', '101', 'provisioning_result', 'Provisioning Result', 'Failed', '9', '{
	"provisioning_result": "Failed"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('119', '101', 'JenkinsLogOutputPath', 'JenkinsLogOutputPath', 'C:\\Users\\Manish\\Downloads\\pipelineResult.json', '20', '{
	"provisioning_result": "Failed",
	"static_code_analysis": "Failed",
	"syntax_check": "Failed",
	"JenkinsLogOutputPath": "pipelineResult.json"
}');

INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('120', '102', 'static_code_analysis', 'Static Code Analysis', 'Failed', '21', '{
	"static_code_analysis": "Successs"
}');

INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('121', '102', 'JenkinsLogOutputPath', 'JenkinsLogOutputPath', 'C:\\Users\\Manish\\Downloads\\pipelineResult.json', '20', '{
	"static_code_analysis": "Successs",
	"JenkinsLogOutputPath": "pipelineResult.json"
}');

INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('122', '103', 'static_code_analysis', 'Static Code Analysis', 'Successs', '21', '{
	"static_code_analysis": "Successs"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('123', '103', 'provisioning_result', 'Provisioning Result', 'Successs', '9', '{
	"provisioning_result": "Successs"
}');
INSERT INTO `nxp_pipelines_logs` (`nxp_pipelines_logs_id`, `nxp_pipelines_id`, `headers_name`, `headers_display`, `headers_value`, `durations`, `logs`) 
VALUES ('124', '103', 'JenkinsLogOutputPath', 'JenkinsLogOutputPath', 'C:\\Users\\Manish\\Downloads\\pipelineResult.json', '20', '{
	"provisioning_result": "Successs",
	"static_code_analysis": "Failed",
	"JenkinsLogOutputPath": "pipelineResult.json"
}');
