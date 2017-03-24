package org.ikeda.vault;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ikeda.vault.model.VaultResponse;
import org.ikeda.vault.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProxyContoller {

    @Autowired
    private VaultService vaultService;

    @RequestMapping(value="/validate", method = {RequestMethod.POST}, produces={"application/json"})
    public VaultResponse testConnection(@RequestParam("token") String vaultToken, @RequestParam("addr") String vaultAddr) {
        String comeback = vaultService.testConnection(vaultToken, vaultAddr);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(comeback);
            JsonNode initNode = node.findValue("initialized");
            JsonNode sealedNode = node.findValue("sealed");
            JsonNode vNode = node.findValue("version");
            VaultResponse vResp = new VaultResponse();
            vResp.setInitialised(initNode.asBoolean());
            vResp.setSealed(sealedNode.asBoolean());
            vResp.setVersion(vNode.asText());


            return vResp;
        } catch (Exception e) {
            VaultResponse vResp = new VaultResponse();
            vResp.setMessage(e.getMessage());
            ResponseEntity<VaultResponse> badRequest = new ResponseEntity<>(vResp, HttpStatus.BAD_REQUEST);
            return vResp;
        }
    }
}
