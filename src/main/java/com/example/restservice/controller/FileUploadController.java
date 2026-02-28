package com.example.restservice.controller;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    /**
     * Health check endpoint to verify that the file upload service is running. This can be used for monitoring and debugging purposes.
     * @return A ResponseEntity with a simple message indicating that the file upload endpoint is working.
     */
    @GetMapping(value="/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("File upload endpoint is working!");
    }

    /**
     * Endpoint to upload a PDF file and extract its text content. The response is a JSON object containing the original filename and the extracted text.
     * @param file The PDF file to be uploaded, sent as multipart/form-data with the key "file".
     * @return A ResponseEntity containing a JSON object with the filename and extractesd text.
     * @throws IOException If there is an error reading the file or processing the PDF.
     */
    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadPDF(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            // todo: replace with constants later on
            return ResponseEntity.badRequest().body(Map.of("error", "No file provided"));
        }
        String filename = file.getOriginalFilename();

        // Get the contents of the file itself.
        byte[] fileBytes = file.getInputStream().readAllBytes();
        PDDocument document = Loader.loadPDF(fileBytes);
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