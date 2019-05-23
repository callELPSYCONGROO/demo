package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author mayuhan
 * @date 2019/5/23 18:07
 */
public class HtmlUtil {

	@Test
	public void h() throws IOException {
		String html = "<li>Credentials Binding Plugin v1.18\n" +
				"                    <ul><li>SSH Credentials Plugin v1.16 failed to load. Fix this plugin first.</li></ul>" +
				"</li>" +
				"<li>Pipeline v2.5\n" +
				"                    <ul><li>Pipeline: Input Step v2.9 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Stage View Plugin v2.11\n" +
				"                    <ul><li>Pipeline: REST API Plugin v2.11 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Build Step v2.7\n" +
				"                    <ul><li>Pipeline: Supporting APIs v2.20 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Declarative v1.3.3.1\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.107.3 or later to run this plugin.</li><li>Pipeline: Basic Steps v2.8.3 failed to load. Fix this plugin first.</li></ul></li><li>GitHub Branch Source Plugin v2.4.2\n" +
				"                    <ul><li>GitHub plugin v1.29.4 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: GitHub Groovy Libraries v1.0\n" +
				"                    <ul><li>Pipeline: Shared Groovy Libraries v2.13 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Multibranch v2.20\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.62 or later to run this plugin.</li><li>Pipeline: Groovy v2.57.3 failed to load. Fix this plugin first.</li></ul></li><li>Docker Commons Plugin v1.14\n" +
				"                    <ul><li>Credentials Binding Plugin v1.18 failed to load. Fix this plugin first.</li></ul></li><li>Docker Pipeline v1.18\n" +
				"                    <ul><li>Pipeline: Basic Steps v2.8.3 failed to load. Fix this plugin first.</li></ul></li><li>Jenkins Git plugin v3.9.4\n" +
				"                    <ul><li>Jenkins Git client plugin v2.7.7 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Nodes and Processes v2.22\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.73.3 or later to run this plugin.</li><li>Pipeline: Supporting APIs v2.20 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Stage Tags Metadata v1.3.3.1\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.107.3 or later to run this plugin.</li></ul></li><li>LDAP Plugin v1.20\n" +
				"                    <ul><li>Jenkins Mailer Plugin v1.23 failed to load. Fix this plugin first.</li></ul></li><li>SSH Credentials Plugin v1.16\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.73.3 or later to run this plugin.</li></ul></li><li>Jenkins Git client plugin v2.7.7\n" +
				"                    <ul><li>Jenkins JSch dependency plugin v0.1.55 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Job v2.25\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.73.3 or later to run this plugin.</li><li>Pipeline: Supporting APIs v2.20 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Basic Steps v2.8.3\n" +
				"                    <ul><li>Jenkins Mailer Plugin v1.23 failed to load. Fix this plugin first.</li></ul></li><li>Jenkins JSch dependency plugin v0.1.55\n" +
				"                    <ul><li>SSH Credentials Plugin v1.16 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Declarative Extension Points API v1.3.3.1\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.107.3 or later to run this plugin.</li><li>Pipeline: Groovy v2.57.3 failed to load. Fix this plugin first.</li></ul></li><li>Email Extension Plugin v2.65\n" +
				"                    <ul><li>Jenkins Mailer Plugin v1.23 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline Graph Analysis Plugin v1.9\n" +
				"                    <ul><li>Pipeline: Groovy v2.57.3 failed to load. Fix this plugin first.</li></ul></li><li>GitHub plugin v1.29.4\n" +
				"                    <ul><li>Display URL API v2.3.1 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Shared Groovy Libraries v2.13\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.107.3 or later to run this plugin.</li><li>Pipeline: Groovy v2.57.3 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Groovy v2.57.3\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.62 or later to run this plugin.</li><li>Pipeline: Supporting APIs v2.20 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Supporting APIs v2.20\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.62 or later to run this plugin.</li></ul></li><li>Jenkins SSH Slaves plugin v1.29.4\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.89.4 or later to run this plugin.</li><li>SSH Credentials Plugin v1.16 failed to load. Fix this plugin first.</li></ul></li><li>Jenkins Mailer Plugin v1.23\n" +
				"                    <ul><li>Display URL API v2.3.1 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: REST API Plugin v2.11\n" +
				"                    <ul><li>Pipeline: Job v2.25 failed to load. Fix this plugin first.</li></ul></li><li>Jenkins Workspace Cleanup Plugin v0.37\n" +
				"                    <ul><li>Pipeline: Nodes and Processes v2.22 failed to load. Fix this plugin first.</li></ul></li><li>Jenkins Subversion Plug-in v2.12.1\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.73.3 or later to run this plugin.</li><li>SSH Credentials Plugin v1.16 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Model API v1.3.3.1\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.107.3 or later to run this plugin.</li></ul></li><li>Display URL API v2.3.1\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.89.4 or later to run this plugin.</li></ul></li><li>Pipeline: Input Step v2.9\n" +
				"                    <ul><li>Pipeline: Supporting APIs v2.20 failed to load. Fix this plugin first.</li></ul></li><li>Pipeline: Declarative Agent API v1.1.1\n" +
				"                    <ul><li>Pipeline: Declarative Extension Points API v1.3.3.1 failed to load. Fix this plugin first.</li></ul></li><li>Branch API Plugin v2.3.0\n" +
				"                    <ul><li>You must update Jenkins from v2.60.3 to v2.107.3 or later to run this plugin.</li></ul></li><li>Jenkins GIT server Plugin v1.7\n" +
				"                    <ul><li>Jenkins Git client plugin v2.7.7 failed to load. Fix this plugin first.</li></ul></li>";
		Document document = Jsoup.parse(html);
		Elements elements = document.getElementsByTag("li");
		List<String> stringList = elements.parallelStream()
				.map(element -> element.text().replaceAll(" ", "-").replaceAll("\n", "").toLowerCase())
				.collect(Collectors.toList());


		String base = "http://updates.jenkins-ci.org/download/plugins/";
		String domain = "http://updates.jenkins-ci.org";
		// 插件下载首页
		Jsoup.connect(base).get()
				// 所有插件链接a标签
				.getElementsByAttribute("href")
				.parallelStream()
				// 在待下载中的a标签
				.filter(element -> {
					String text = element.text();
					String textU = text.substring(0, text.length() - 1).toLowerCase();
					for (String pluginDesc : stringList) {
						if (pluginDesc.contains(textU)) {
							return true;
						}
					}
					return false;
				})
				// 插件下载页面
				.map(element -> base + element.attr("href"))
				.collect(Collectors.toList())
				.parallelStream()
				// 最新版下载地址
				.map(pluginUrl -> {
					try {
						// 插件版本页面
						return Jsoup.connect(pluginUrl).get()
								// 获取a标签
								.getElementsByTag("a")
								.parallelStream()
								// 获取最新版
								.filter(pluginUrlDocumentElement -> pluginUrlDocumentElement.text().contains("latest"))
								// 最新版下载地址
								.map(pluginUrlDocumentElement -> domain + pluginUrlDocumentElement.attr("href"))
								.findFirst()
								.orElse(null);
					} catch (IOException e) {
						return null;
					}
				})
				.collect(Collectors.toList())
				.parallelStream()
				.filter(Objects::nonNull)
				.forEach(url -> {
					// 下载文件的文件名
					String fileName = url.substring(url.lastIndexOf("/"));
					// 下载文件目录
					File file = new File("C:\\Users\\SwordNoTrace\\Desktop\\jenkins" + File.separatorChar + fileName);
					try {
						ResponseEntity<byte[]> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), byte[].class);
						byte[] bytes = responseEntity.getBody();
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(bytes, 0, bytes.length);
						System.out.println(fileName + "----->下载成功");
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("文件下载发生异常：【" + fileName + "】：" + e.getMessage());
					}
				});
	}
}
