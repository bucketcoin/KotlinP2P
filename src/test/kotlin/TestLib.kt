import dev.crash.p2p.P2PClient
import dev.crash.p2p.P2PServer
import kotlinx.coroutines.delay

suspend fun main(){
    P2PServer("127.0.0.1", 8000)
    P2PClient("127.0.0.1", 8000)
    while (true){
        delay(1000)
    }
}