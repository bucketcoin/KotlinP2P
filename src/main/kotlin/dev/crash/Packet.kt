package dev.crash

import dev.crash.p2p.P2PChannel

abstract class Packet(val id: Int) {
    abstract fun createPacket(): BytePacket

    fun send(channel: P2PChannel){
        val packet = BytePacket()
        packet.writeAsVarInt(id)
        packet.write(createPacket())
        channel.sendPacket(packet)
    }
}