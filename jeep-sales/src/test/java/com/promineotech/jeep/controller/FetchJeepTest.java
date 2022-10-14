package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest extends FetchJeepTestSupport {

  @Autowired
  @Getter // Not in the W14 coding instructions. Rewatching the videos helped me understand Lombok annotations
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int serverPort;
  
  protected String getBaseUri() {
	  return String.format("http://localhost:%d/jeeps", serverPort);
	  
  }
  
  @Test
  void testThatJeepAreReturnedWhenAValidModelAndTrimAreSupplied() {
  
    // Given: a valid model, trim, and URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri =
    	String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);	
    	// String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim); // W15 Reading Data with Spring ts@24:43

    // When: a connection is made to he URI
    ResponseEntity<List<Jeep>> response =
        getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}); // changed from W13 - #13.a
        
    // Then: a success (OK or 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // From W13 Coding #13.b
    
    // And: the actual list returned is the same as the expected list
    List<Jeep> actual = response.getBody();
    List<Jeep> expected = buildExpected();
    
//    actual.forEach(jeep -> jeep.setModelPK(null));
    
    // Used to see the toString() looks like 
    // System.out.println(expected);
    assertThat(actual).isEqualTo(expected);
    }
}