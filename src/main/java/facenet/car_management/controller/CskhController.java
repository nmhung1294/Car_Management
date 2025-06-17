package facenet.car_management.controller;

import facenet.car_management.dto.CskhDTO;
import facenet.car_management.service.CskhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/care")
public class CskhController {
    @Autowired
    CskhService cskhService;

    @GetMapping
    public ResponseEntity<List<Object[]>> getAll(){
        return ResponseEntity.ok(cskhService.getCskh());
    }

    @PostMapping
    public ResponseEntity<CskhDTO> getAll(@RequestBody CskhDTO cskhDTO) throws Exception {
        return  ResponseEntity.ok(cskhService.createCskhSchedule(cskhDTO));
    }

}
