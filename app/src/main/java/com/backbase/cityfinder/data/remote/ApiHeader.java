package com.backbase.cityfinder.data.remote;

import com.backbase.cityfinder.di.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;

    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class ProtectedApiHeader {
        //add any protected API header, if required
        @Inject
        public ProtectedApiHeader() {
        }
    }

    public static final class PublicApiHeader {

        private String contentType;

        @Inject
        public PublicApiHeader(@ApiInfo String contentType) {
            this.contentType = contentType;
        }
    }

}
