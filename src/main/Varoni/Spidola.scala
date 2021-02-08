package Varoni
import Player.Character

class Spidola extends Character (true, 60, 60, 5, 10, 50, 80, 100, 100){

  override def lvlup(): Unit = {
    if(this.exp >= (100 * this.lvl)){
      this.lvl += (exp.toFloat/100f).floor.toInt

      this.maxhp += 2 * (exp.toFloat/100f).floor.toInt
      this.hp += (this.maxhp - this.hp)

      this.attk += 1 * (exp.toFloat/100f).floor.toInt
      this.deff += 2 * (exp.toFloat/100f).floor.toInt

      this.magattk += 3 * (exp.toFloat/100f).floor.toInt
      this.magdeff += 3 * (exp.toFloat/100f).floor.toInt

      this.maxmana +=  5 * (exp.toFloat/100f).floor.toInt
      this.mana += (this.maxmana - this.mana)

      this.exp -= 100 * (exp.toFloat / 100f).floor.toInt
    }
  }

  // healing
  def heal(cits_varonis: Character): Unit = {
    if(cits_varonis.hp < cits_varonis.maxhp - 20){
      cits_varonis.hp += 20
    }
    else if(cits_varonis.hp < cits_varonis.maxhp){
      cits_varonis.hp += (cits_varonis.maxhp - cits_varonis.hp)
    }
  }

  // revives fallen teammate
  def revive(cits_varonis: Character): Unit = {
    if(!cits_varonis.isAlive){
      cits_varonis.isAlive = true
    }
  }


  override def battleOptions(): List[String] = {
    var actions: List[String] = List("Apburtais Pieskāriens", "Saules Dūriens", "Gara Cēlums", "Atmostas Varoņi")
    actions
  }

  override def takeAction(action: String, cits_varonis: Character): Unit = {
    val actions = battleOptions()
    if (action == actions.head && !cits_varonis.isAlly) {
      this.magAttack(cits_varonis)
    }
    else if (action == actions(1) && !cits_varonis.isAlly){
      this.physAttack(cits_varonis)
    }
    else if(action == actions(2) && isAlly){
      heal(cits_varonis)
    }
    else if(action == actions(3) && this.lvl >= 15 && isAlly){
      revive(cits_varonis)
    }
  }
}
