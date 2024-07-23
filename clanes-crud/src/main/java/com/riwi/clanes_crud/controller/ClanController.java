package com.riwi.clanes_crud.controller;

import com.riwi.clanes_crud.dto.request.ClanReq;
import com.riwi.clanes_crud.dto.request.ClanUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.riwi.clanes_crud.dto.request.ClanGetReq;
import com.riwi.clanes_crud.entities.Clan;
import com.riwi.clanes_crud.services.abtract_service.IClanService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clan")
@AllArgsConstructor
public class ClanController {
    @Autowired
    private final IClanService clanService;

    @GetMapping()
    public ResponseEntity<Page<Clan>> getAll(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "3") Integer size,
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String description,
        @RequestParam(required = false) Boolean isActive,
        @RequestParam(required = false) Long cohortId
    ) {
        ClanGetReq req = ClanGetReq.builder()
            .page(page-1)
            .size(size)
            .name(name)
            .description(description)
            .isActive(isActive)
            .cohortId(cohortId)
            .build();

        return ResponseEntity.ok(this.clanService.findAll(req));
    }

    @PostMapping
    public  ResponseEntity<Clan> create(@RequestBody ClanReq req){
        return ResponseEntity.ok(this.clanService.create(req));
    }

    @PutMapping ("/{id}")
    public ResponseEntity <Clan> update (@RequestParam ClanUpdateReq req,
                                         @PathVariable Long id){
        return ResponseEntity.ok(this.clanService.update(id,req));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity <Clan>disable (@PathVariable Long id ){
        return ResponseEntity.ok(this.clanService.disable(id));
    }
}
