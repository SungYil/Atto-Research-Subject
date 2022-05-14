package com.atto.AttoSubject;

import com.atto.AttoSubject.dtos.HostRegisterRequest;
import com.atto.AttoSubject.dtos.HostUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AttoSubjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads(){
	}

	@Test
	void whenUpdateHost_thenOk()throws Exception{
		var result=requestPut(new HostUpdateRequest("140.82.113.3","github"),"/host/22");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenUpdateHostWithDuplicateName_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140.82.113.3","github"),"/host/22");

		result.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void whenUpdateHostWithDuplicateIp_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140.82.113.3","github"),"/host/22");

		result.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void whenUpdateHostWithWrongRequestForm_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140","github"),"/host/22");

		result.andDo(print()).andExpect(status().isBadRequest());
	}
	@Test
	void whenUpdateHostWithNotFoundHost_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140.10.10.10","github"),"/host/132");

		result.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void whenUpdate_thenOK()throws Exception{
		/*var result = requestPost(new HostUpdateRequest("140.82.113.3",""),"/host/{name}");

		result.andDo(print()).andExpect(status().isOk());*/
	}

	@Test
	void whenGetHosts_thenOk()throws Exception{
		var result=requestGet("/host/getHosts");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenGetHost_thenOk()throws Exception{
		MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
		params.add("ip","142.250.138.101");
		var result=requestGet(params,"/host/getHost");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenRegister_thenOk()throws Exception{
		var result=requestPost(new HostRegisterRequest("142.250.138.101","google"),"/host/register");

		result.andDo(print()).andExpect(status().isOk());
	}
	ResultActions requestGet(MultiValueMap<String, String> params, String path) throws Exception{
		return mockMvc.perform(
			MockMvcRequestBuilders.get(path).params(params)
		);

	}
	ResultActions requestGet(String path)throws Exception{
		return requestGet(new LinkedMultiValueMap<>(), path);
	}
	ResultActions requestPost(Object request,String path) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.post(path)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(request))
		);
	}
	ResultActions requestPut(Object request,String path)throws Exception{
		return mockMvc.perform(
				MockMvcRequestBuilders.put(path)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(request))
		);
	}
}
