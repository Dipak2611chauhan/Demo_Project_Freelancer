package com.demo.service;

import com.demo.model.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileService {

    private static final String FILE_PATH = "src/main/resources/example.txt";

    public ResponseDTO readFileContent() {
        ResponseDTO response = new ResponseDTO();
        try {
            String content = Files.readString(Paths.get(FILE_PATH));
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("File content read successfully.");
            response.setData(content);
        } catch (IOException e) {
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Error reading file content.");
        }
        return response;
    }

    public ResponseDTO rewriteFileContent(String content) {
        ResponseDTO response = new ResponseDTO();
        try {
            Files.writeString(Paths.get(FILE_PATH), content);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("File content has been rewritten successfully.");
        } catch (IOException e) {
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Error rewriting file content.");
        }
        return response;
    }

    public ResponseDTO appendToFile(String content) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Files.write(Paths.get(FILE_PATH), content.getBytes(), StandardOpenOption.APPEND);
            responseDTO.setHttpStatus(HttpStatus.OK);
            responseDTO.setMessage("Content has been appended to the file.");
        } catch (IOException e) {
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("Error appending content to the file.");
        }
        return responseDTO;
    }

    public ResponseDTO deleteContent(String content) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            // Read file content
            ResponseDTO readResponse = readFileContent();

            if (readResponse.getHttpStatus() != HttpStatus.OK) {
                // Return response if reading file content fails
                responseDTO.setHttpStatus(readResponse.getHttpStatus());
                responseDTO.setMessage("Error reading file content.");
                return responseDTO;
            }

            String fileContent = readResponse.getData();

            // Replace content to delete
            fileContent = fileContent.replace(content, "");

            // Rewrite file content
            ResponseDTO rewriteResponse = rewriteFileContent(fileContent);

            if (rewriteResponse.getHttpStatus() != HttpStatus.OK) {
                // Return response if rewriting file content fails
                responseDTO.setHttpStatus(rewriteResponse.getHttpStatus());
                responseDTO.setMessage("Error rewriting file content.");
                return responseDTO;
            }

            responseDTO.setHttpStatus(HttpStatus.OK);
            responseDTO.setMessage("Content has been deleted from the file.");
        } catch (Exception e) {
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("An error occurred while deleting content from the file.");
        }

        return responseDTO;
    }
}
