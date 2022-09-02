package com.mifuyu.restClient;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringRestClientTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestClientTutorialApplication.class, args);
	}

	//restTemplateインスタンスを生成する
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory()).build();
//	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	//起動時に RestTemplate を実行する（その結果、引用を取得する）
	//CREATE
//	@Bean
//	public CommandLineRunner insert(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			System.out.println("------------------1件追加開始------------------");
//
//			Customer customer = new Customer();
//			customer.setId("100");
//			customer.setUsername("user100");
//			customer.setEmail("test.user.100@EXAMPLE.com");
//			customer.setPhoneNumber("01234567890");
//			customer.setPostCode("1234567");
//			// APIにPOSTでリクエストする
//			customer = restTemplate.postForObject(
//					"http://localhost:8082/api/customers", customer, Customer.class);
//			System.out.println(customer.toString());
//			System.out.println("------------------1件追加完了------------------");
//		};
//	}
//	//READ
//	@Bean
//	public CommandLineRunner selectAll(RestTemplate restTemplate) throws Exception {
//
//		return args -> {
//			System.out.println("------------------全件取得開始------------------");
//			// APIにGETでリクエストする
//			// 第一引数：URL、第二引数：レスポンスの型
//			Customer[] customers = restTemplate.getForObject(
//					"http://localhost:8082/api/customers", Customer[].class);
//			for (Customer customer: customers){
//				System.out.println(customer.toString());
//			}
//			System.out.println("------------------全件取得完了------------------");
//		};
//	}
//	//READ
//	@Bean
//	public CommandLineRunner selectById(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			System.out.println("------------------1件取得開始------------------");
//			// APIにGETでリクエストする
//			// 第一引数：URL、第二引数：レスポンスの型
//			Customer customer = restTemplate.getForObject(
//					"http://localhost:8082/api/customers/001", Customer.class);
//			System.out.println(customer.toString());
//			System.out.println("------------------1件取得完了------------------");
//		};
//	}
	//UPDATE
	@Bean
	public CommandLineRunner update(RestTemplate restTemplate) throws Exception {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);
		return args -> {
			System.out.println("------------------1件更新開始------------------");

			Customer customer = restTemplate.getForObject(
					"http://localhost:8082/api/customers/100", Customer.class);
			System.out.println(customer.toString());
			//データ更新
			customer.setPostCode("1111111");

			// APIにPATCHでリクエストする
			customer = restTemplate.patchForObject(
					"http://localhost:8082/api/customers", customer, Customer.class);

			System.out.println(customer.toString());
			System.out.println("------------------1件更新完了------------------");
		};
	}
//	//DELETE
//	@Bean
//	public CommandLineRunner deleteById(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			System.out.println("------------------1件削除開始------------------");
//			restTemplate.delete(
//					"http://localhost:8082/api/customers/100", Customer.class);
//			System.out.println("------------------1件削除完了------------------");
//		};
//	}



}
