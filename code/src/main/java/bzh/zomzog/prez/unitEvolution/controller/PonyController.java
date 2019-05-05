package bzh.zomzog.prez.unitEvolution.controller;

import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import bzh.zomzog.prez.unitEvolution.domain.PonyType;
import bzh.zomzog.prez.unitEvolution.service.PonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ponies")
public class PonyController {

    @Autowired
    private PonyService ponyService;

    @GetMapping
    public ResponseEntity<List<PonyDto>> listAll(@RequestParam(required = false) PonyType type) {
        List<PonyDto> ponies = ponyService.listAll(type);

        return ResponseEntity.ok(ponies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PonyDto> listAll(@PathVariable String id) {

        return ponyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PonyDto> save(@RequestBody PonyDto pony) {

        PonyDto created = ponyService.save(pony);
        return ResponseEntity.ok(created);

    }
}
