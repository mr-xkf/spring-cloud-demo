package org.xkf.cloudprovider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudProviderApplicationTests {


	@Autowired
	private RestTemplate restTemplate;


	@Test
	public void contextLoads() {
		/*ResponseEntity<String> forEntity = restTemplate.getForEntity("https://api.weixin.qq" +
				".com/cgi-bin/token?grant_type=client_credential&appid=wx159067bdddf98d81&secret" +
				"=93bec84be73189dcc3b2299277ad50ab", String.class);
		String body = forEntity.getBody();
		System.out.println(body);*/


		ResponseEntity<String> forEntity = restTemplate.getForEntity("https://api.weixin.qq" +
				".com/cgi-bin/get_current_selfmenu_info?access_token=26_4fomn12" +
				"-MFWcujU_tNT3ubGNGI1t74__byAs5vBoSi1pyiNrYYwq0LB8AuwbzUcgtU_1bi1JDllE6QGf9iScQd-Bt7ntw" +
				"-cKPZgdTX_2chDpEBWFNFRQ1vyBGX7TagcEcYWQaPbjOPFaKUowDHShAEALPA", String.class);
		String body = forEntity.getBody();
		System.out.println(body);


	}

}
