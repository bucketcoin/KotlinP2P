package dev.crash

import dev.crash.networking.PacketType
import dev.crash.p2p.P2PChannel

abstract class PacketHandler(val packetType: PacketType) {
    abstract fun handle(channel: P2PChannel, packet: BytePacket)
}