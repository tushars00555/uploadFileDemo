package com.srajan.repository;

import com.srajan.domain.UploadFile;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by tushar on 22/6/17.
 */

public interface UploadFileRepo extends MongoRepository<UploadFile,String> {
}
