package dev.crash.p2p

import dev.crash.BytePacket
import dev.crash.p2p.P2PHandler
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking

class P2PChannel(val socket: Socket, val handler: P2PHandler) {

    val input = socket.openReadChannel()
    val output = socket.openWriteChannel(autoFlush = true)

    fun open(){
        runBlocking {
            handler.onConnect(this@P2PChannel)
            while (!socket.isClosed) {
                input.awaitContent()
                if(input.availableForRead == 0) continue
                val bytes = ByteArray(input.availableForRead)
                input.readAvailable(bytes)
                handler.handle(this@P2PChannel, bytes)
            }
            handler.onDisconnect(this@P2PChannel)
        }
    }

    fun sendBytes(bytes: ByteArray) = runBlocking { try{output.writeFully(bytes)}catch(ex: Exception){} }

    fun sendPacket(bytePacket: BytePacket) = sendBytes(bytePacket.toByteArray())

    fun close() = socket.close()
}