plugins {
        id 'org.springframework.boot' version '2.2.6.RELEASE'
        id 'io.spring.dependency-management' version '1.0.9.RELEASE'
        id 'java'
        }

        group = 'pl.nullpointerexception'
        version = '0.0.1-SNAPSHOT'
        sourceCompatibility = '11'

        configurations {
        compileOnly {
        extendsFrom annotationProcessor
        }
        }

        compileJava {
        options.compilerArgs << '-parameters'
        }

        repositories {
        mavenCentral()
        }

        dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'org.springframework.boot:spring-boot-starter-security'
        implementation 'org.springframework.boot:spring-boot-starter-cache'
        implementation 'com.auth0:java-jwt:3.8.3'
        implementation 'org.liquibase:liquibase-core'

        implementation 'org.ehcache:ehcache:3.8.1'
        implementation 'javax.cache:cache-api:1.1.1'

        compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
        compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'

        runtimeOnly 'mysql:mysql-connector-java'
        compileOnly 'org.projectlombok:lombok'

        annotationProcessor 'org.projectlombok:lombok'
        testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
        }
        testImplementation 'org.springframework.security:spring-security-test'
        }

        test {
        useJUnitPlatform()
        }

        task generatePostsAndComments() {
        doLast {
        File dataSql = file("src/main/resources/data.sql")
        dataSql.write("")
        //posty
        for (int i = 1; i <= 100; i++) {
        dataSql.append("insert into post(id, title, content, created) " +
        "values (${i}, 'Test post ${i}', 'Content ${i}', '"+ LocalDateTime.now().minusDays(100 - i) +"');\n")
        }
        //komentarze
        for (int i = 1; i <= 100; i++) {
        int postId = 1 + i / 10
        dataSql.append("insert into comment(id, post_id, content, created) " +
        "values (${i}, ${postId}, 'Comment ${i}', '"+ LocalDateTime.now().minusDays(100 - i) +"');\n")
        }
        }
        }
