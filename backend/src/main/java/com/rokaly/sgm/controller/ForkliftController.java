//package com.rokaly.sge.controller;
//
//import com.rokaly.sge.dto.ForkliftDTO;
//import com.rokaly.sge.dto.GetForkliftDTO;
//import com.rokaly.sge.dto.PutForkliftDTO;
//import com.rokaly.sge.model.Forklift;
//import com.rokaly.sge.repository.ForkliftRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@RestController
//@RequestMapping("forklifts")
//public class ForkliftController {
//
//    @Autowired
//    private ForkliftRepository repository;
//
//    @PostMapping
//    @Transactional
//    public ResponseEntity<ForkliftDTO> create(@RequestBody ForkliftDTO data, UriComponentsBuilder uriBuilder) {
//        Forklift forklift = new Forklift(data);
//        repository.save(forklift);
//
//        var uri = uriBuilder.path("/forklifts/{id}").buildAndExpand(forklift.getId()).toUri();
//        ForkliftDTO dto = new ForkliftDTO(data);
//        return ResponseEntity.created(uri).body(dto);
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<GetForkliftDTO>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
//        Page<GetForkliftDTO> page = repository.findAll(pagination).map(GetForkliftDTO::new);
//        return ResponseEntity.ok(page);
//    }
//
//    @PutMapping
//    @Transactional
//    public ResponseEntity<GetForkliftDTO> put(@RequestBody PutForkliftDTO data) {
//        Forklift forklift = repository.getReferenceById(data.id());
//        forklift.updateData(data);
//        return ResponseEntity.ok(new GetForkliftDTO(forklift));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        Forklift forklift = repository.getReferenceById(id);
//        forklift.deleteForklift();
//        return ResponseEntity.noContent().build();
//    }
//}
