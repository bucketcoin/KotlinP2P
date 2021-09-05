package dev.crash

import dev.crash.networking.PacketType
import dev.crash.p2p.P2PChannel

abstract class Packet(val packetType: PacketType) {
    abstract fun createPacket(): BytePacket

    fun send(channel: P2PChannel){
        val packet = BytePacket()
        packet.writeAsVarInt(packetType.packetId)
        packet.write(createPacket())
        channel.sendPacket(packet)
    }
}