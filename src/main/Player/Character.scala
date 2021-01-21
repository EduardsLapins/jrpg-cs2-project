package Player

class Character (var maxhp: Int, var hp: Int, var attk: Int, var deff: Int, var magattk: Int, var magdeff: Int, var maxmana: Int, var mana: Int){

  var isAlive: Boolean = true

  def takeDamage(attkStrength: Int): Unit ={
    if (this.hp > 0) {
      this.hp -= attkStrength
      if (this.hp <= 0){
        isAlive = false
      }
    }
    else{
      isAlive = false
    }

  }
}
