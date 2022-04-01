package com.vxplore.agoraaudiocall.tokener.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
