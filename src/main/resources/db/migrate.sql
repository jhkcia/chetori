SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS branchs;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS evaluation_criterion;
DROP TABLE IF EXISTS evaluation_form_type;
DROP TABLE IF EXISTS evaluation_form_type_element;
DROP TABLE IF EXISTS assessment_interpretation_method;
DROP TABLE IF EXISTS quantitive_range_interpretion;
DROP TABLE IF EXISTS qualitative_value_interpretion;
DROP TABLE IF EXISTS evaluation_form_entity;
DROP TABLE IF EXISTS evaluation_form_entity_element;
SET FOREIGN_KEY_CHECKS = 1;

create table users    
(
    user_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(50) not null,
    role_name varchar(50) not null,
    last_name varchar(50) not null,
    personnel_code varchar(50) not null,
    password varchar(50) not null,
    token varchar(50) ,
    evaluator_id bigint,    
    branch_id bigint,
    evaluation_form_type_id bigint
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table branchs    
(
    branch_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) not null,
    address varchar(50) not null,
    phone_number varchar(50) not null
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table evaluation_criterion    
(
    criterion_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) not null,
    type varchar(50) not null,
    reward_method varchar(50) not null,
    punishment_method varchar(50) not null,
    no_reward_punishment_method varchar(50) not null
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table evaluation_form_type    
(
    form_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    name varchar(50)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table evaluation_form_type_element    
(
    element_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    criterion_id bigint not null ,
    type varchar(50) not null,
    form_id bigint not null 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table assessment_interpretation_method    
(
    id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    form_element_id bigint not null ,
    type varchar(50) not null
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table quantitive_range_interpretion    
(
    id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    application_method_id bigint not null ,
    interpretation varchar(50) not null,
    lower_bound int not null,
    upper_bound int not null
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table qualitative_value_interpretion    
(
    id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    application_method_id bigint not null ,
    interpretation varchar(50) not null,
    value varchar(50) not null,
    description varchar(50) not null
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table evaluation_form_entity
(
    entity_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    owner_id bigint not null ,
    evaluator_id bigint not null ,
    form_type_id bigint not null 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table evaluation_form_entity_element    
(
    element_id bigint not null  PRIMARY KEY AUTO_INCREMENT,
    application_method_id bigint not null ,
    entity_element_id bigint not null, 
    result varchar(50) not null,
    value varchar(50) not null
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE users
ADD CONSTRAINT FK_User_Evaluator
FOREIGN KEY (evaluator_id) REFERENCES users(user_id);

ALTER TABLE users
ADD CONSTRAINT FK_User_EvaluationFormType
FOREIGN KEY (evaluation_form_type_id) REFERENCES evaluation_form_type(form_id);

ALTER TABLE users
ADD CONSTRAINT FK_User_Branch
FOREIGN KEY (branch_id) REFERENCES branchs(branch_id);

ALTER TABLE evaluation_form_type_element
ADD CONSTRAINT FK_EvaluationFormTypeElement_EvaluationForm
FOREIGN KEY (form_id) REFERENCES evaluation_form_type(form_id);

ALTER TABLE evaluation_form_type_element
ADD CONSTRAINT FK_EvaluationFormTypeElement_EvaluationCriterion
FOREIGN KEY (criterion_id) REFERENCES evaluation_criterion(criterion_id);

ALTER TABLE assessment_interpretation_method
ADD CONSTRAINT FK_AssessmentAndInterpretationMethod_EvaluationFormTypeElement
FOREIGN KEY (form_element_id) REFERENCES evaluation_form_type_element(element_id);

ALTER TABLE qualitative_value_interpretion
ADD CONSTRAINT FK_QualitativeValueInterpretation_ApplicationMethod
FOREIGN KEY (application_method_id) REFERENCES assessment_interpretation_method(id);

ALTER TABLE quantitive_range_interpretion
ADD CONSTRAINT FK_QuantitiveRangeInterpretation_ApplicationMethod
FOREIGN KEY (application_method_id) REFERENCES assessment_interpretation_method(id);

ALTER TABLE evaluation_form_entity
ADD CONSTRAINT FK_EvaluationFormEntity_OwnerUser
FOREIGN KEY (owner_id) REFERENCES users(user_id);

ALTER TABLE evaluation_form_entity
ADD CONSTRAINT FK_EvaluationFormEntity_EvaluatorUser
FOREIGN KEY (evaluator_id) REFERENCES users(user_id);

ALTER TABLE evaluation_form_entity
ADD CONSTRAINT FK_EvaluationFormEntity_FormType
FOREIGN KEY (form_type_id) REFERENCES evaluation_form_type(form_id);

ALTER TABLE evaluation_form_entity_element
ADD CONSTRAINT FK_EvaluationFormEntityElement_ApplicationMethod
FOREIGN KEY (entity_element_id) REFERENCES evaluation_form_entity(entity_id);

ALTER TABLE evaluation_form_entity_element
ADD CONSTRAINT FK_EvaluationFormEntityElement_EvaluationFormEntity
FOREIGN KEY (application_method_id) REFERENCES assessment_interpretation_method(id);