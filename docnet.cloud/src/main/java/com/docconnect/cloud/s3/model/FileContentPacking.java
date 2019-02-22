package com.docconnect.cloud.s3.model;
public enum FileContentPacking {
    RAW,
    COMPRESSION_ZIP,
    COMPRESSION_GZIP,
    INPLACE_COMPRESSION_GZIP,
    COMPRESSION_TAR,
    COMPRESSION_TGZ
}
