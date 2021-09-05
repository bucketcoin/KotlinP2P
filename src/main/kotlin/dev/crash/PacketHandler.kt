package dev.crash

import dev.crash.p2p.P2PChannel

abstract class PacketHandler(val id: Int) {
    abstract fun handle(channel: P2PChannel, packet: BytePacket)
}