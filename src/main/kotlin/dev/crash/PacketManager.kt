package dev.crash

import dev.crash.p2p.P2PChannel

object PacketManager {
    val packets: HashMap<Int, PacketHandler> = hashMapOf()

    fun handlePacket(channel: P2PChannel, bytes: ByteArray) {
        val packet = BytePacket(bytes)
        while (packet.bytes.size > packet.readPos){
            val packetId = packet.readVarInt()
            if(packets.containsKey(packetId)){
                try {
                    packets[packetId]!!.handle(channel, packet)
                }catch (ex: Exception) {
                    ex.printStackTrace()
                    channel.close()
                    break
                }
            }else {
                println("Unhandled Packet with packet id $packetId")
                channel.close()
                break
            }
        }
    }
}