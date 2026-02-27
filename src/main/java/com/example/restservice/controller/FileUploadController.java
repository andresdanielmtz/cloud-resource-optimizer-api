package com.example.restservice.controller;

import org.apache.coyote.Response;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    @GetMapping(value="/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("File upload endpoint is working!");
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadPDF(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            // todo: replace with constants later on
            return ResponseEntity.badRequest().body(Map.of("error", "No file provided"));
        }
        String filename = file.getOriginalFilename();

        // Get the contents of the file itself.
        File tempFile = File.createTempFile("upload-", ".pdf");
        PDDocument document = Loader.loadPDF(tempFile);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();


        // Create response with the body as the body of the PDF text. This is a JSON response.
        Map<String, String> response = new HashMap<>();
        response.put("name", filename);
        response.put("text", text);

        return ResponseEntity.ok(response);
    }
}