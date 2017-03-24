package org.ikeda.vault;

import org.ikeda.vault.model.VaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProxyContoller {

    @CrossOrigin(origins={"*"})
    @RequestMapping(value="/validate", method = RequestMethod.POST, produces={"application/json"})
    public ResponseEntity<VaultResponse> testConnection(@RequestParam("token") String vaultToken, @RequestParam("addr") String vaultAddr) {
        VaultResponse vResp = new VaultResponse();
        vResp.setMessage("OK");
        ResponseEntity<VaultResponse> resp = new ResponseEntity<>(vResp, HttpStatus.OK);
        return resp;
    }
}
