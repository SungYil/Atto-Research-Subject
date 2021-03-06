package com.atto.AttoSubject;

import com.atto.AttoSubject.dtos.HostPostRequest;
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
	void whenPostHostAliveHistoryById_thenOk()throws Exception{
		var result=requestPost(null,"/hosts/43/host-alive-history");

		result.andDo(print()).andExpect(status().isOk());
	}
	@Test
	void whenPostHostAliveHistoryByIdWithNotFoundId_thenError()throws Exception{
		var result=requestPost(null,"/hosts/21243/host-alive-history");

		result.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	void whenGetHostAliveHistory_thenOk()throws Exception{
		var result=requestGet("/hosts/host-alive-history");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenGetHostByIdAliveHistory_thenOk()throws Exception{
		var result=requestGet("/hosts/43/host-alive-history");

		result.andDo(print()).andExpect(status().isOk());
	}
	@Test
	void whenGetHostByIdAliveHistoryWithNotFoundId_thenError()throws Exception{
		var result=requestGet("/hosts/23123/host-alive-history");

		result.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	void whenDeleteHostById_thenOk()throws Exception{
		var result =requestDelete("/hosts/39");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenDeleteHostByIdWithNotFoundId_thenError()throws Exception{
		var result=requestDelete("/hosts/23132");

		result.andDo(print()).andExpect(status().isNotFound());
	}


	@Test
	void whenUpdateHostById_thenOk()throws Exception{
		var result=requestPut(new HostUpdateRequest("142.250.138.101","google"),"/hosts/43");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenUpdateHostByIdWithDuplicateName_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140.82.113.3","google"),"/hosts/39");

		result.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void whenUpdateHostByIdWithDuplicateIp_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("142.250.138.101","github"),"/hosts/39");

		result.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void whenUpdateHostByIdWithWrongRequestForm_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140","github"),"/hosts/39");

		result.andDo(print()).andExpect(status().isBadRequest());
	}
	@Test
	void whenUpdateHostByIdWithNotFoundHost_thenError()throws Exception{
		var result=requestPut(new HostUpdateRequest("140.10.10.10","github"),"/hosts/132");

		result.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	void whenGetHost_thenOk()throws Exception{
		var result=requestGet("/hosts");

		result.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void whenGetHostById_thenOk()throws Exception{
		var result=requestGet("/hosts/24");

		result.andDo(print()).andExpect(status().isOk());
	}
	@Test
	void whenGetHostByIdWithNotFoundId_thenError()throws Exception{
		var result=requestGet("/hosts/1002");

		result.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	void whenPostHost_thenOk()throws Exception{
		var result=requestPost(new HostPostRequest("143.251.33.141","youtube13"),"/hosts");

		result.andDo(print()).andExpect(status().isOk());
	}
	@Test
	void whenPostHostWithMaxCount_thenError()throws Exception{
		var result=requestPost(new HostPostRequest("143.251.33.33","google233"),"/hosts");

		result.andDo(print()).andExpect(status().isBadRequest());
	}
	@Test
	void whenPostHostWithIpTypeError_thenError()throws Exception{
		var result=requestPost(new HostPostRequest("142250138101","google"),"/hosts");

		result.andDo(print()).andExpect(status().isBadRequest());
	}
	@Test
	void whenPostHostWithDuplicatedIp_thenError()throws Exception{
		var result=requestPost(new HostPostRequest("142.250.138.101","google1"),"/hosts");

		result.andDo(print()).andExpect(status().isBadRequest());
	}
	@Test
	void whenPostHostWithDuplicatedName_thenError()throws Exception{
		var result=requestPost(new HostPostRequest("142.250.131.101","google"),"/hosts");

		result.andDo(print()).andExpect(status().isBadRequest());
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
	ResultActions requestDelete(String path)throws Exception{
		return mockMvc.perform(
				MockMvcRequestBuilders.delete(path)
		);
	}
}
