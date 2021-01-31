package Player

import scala.collection.mutable.ListBuffer

class Party (party: List[Character]){

  def partyExp (loserParty: List[Character]): Unit ={
    var loserPartyExp: Int = 0
    var aliveList: ListBuffer[Character] = ListBuffer()

    for (i <- loserParty){
      loserPartyExp += i.exp
    }

    for (j <- this.party){
      if (j.isAlive){
        aliveList += j
      }
    }

    for (k <- aliveList){
      k.exp += (loserPartyExp / aliveList.length)
      k.lvlup()
    }
  }
}
