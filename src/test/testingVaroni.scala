import org.scalatest._
import Player.Character
import Varoni.Lacplesis
import Varoni.Spidola

class testingVaroni extends FunSuite{
  test("initial stats and battle options") {
    class Bulky extends Character(false, 500, 500, 10, 50, 10, 50, 30, 30)
    val BulkyEnemy: Character = new Bulky()
    val Lacplesis: Character = new Lacplesis()
    val Spidola: Character = new Spidola()

    //stats for Lacplesis
    assert(Lacplesis.isAlly)
    assert(Lacplesis.maxhp == 100)
    assert(Lacplesis.hp == 100)
    assert(Lacplesis.attk == 30)
    assert(Lacplesis.deff == 25)
    assert(Lacplesis.magattk == 0)
    assert(Lacplesis.magdeff == 10)
    assert(Lacplesis.maxmana == 0)
    assert(Lacplesis.mana == 0)

    //stats for Spidola
    assert(Spidola.isAlly)
    assert(Spidola.maxhp == 60)
    assert(Spidola.hp == 60)
    assert(Spidola.attk == 5)
    assert(Spidola.deff == 10)
    assert(Spidola.magattk == 50)
    assert(Spidola.magdeff == 80)
    assert(Spidola.maxmana == 100)
    assert(Spidola.mana == 100)

    //battle options for Lacplesis
    //basic attack
    Lacplesis.takeAction("Zobena Cirtiens", BulkyEnemy)
    assert(BulkyEnemy.hp == 482)
    //sleeping
    BulkyEnemy.physAttack(Lacplesis)
    assert(Lacplesis.hp == 96)
    Lacplesis.takeAction("Gulēt", Lacplesis)
    assert(Lacplesis.hp == 100)
    //locked, attack does not work
    Lacplesis.takeAction("Lāča Dūre", BulkyEnemy)
    assert(BulkyEnemy.hp == 482)
    //locked attack does not work
    Lacplesis.takeAction("Lāča Spēks", BulkyEnemy)
    assert(BulkyEnemy.hp == 482)

    //battle options for Spidola
    //basic magattack
    Spidola.takeAction("Apburtais Pieskāriens", BulkyEnemy)
    assert(BulkyEnemy.hp == 432)
    //sleeping
    BulkyEnemy.physAttack(Spidola)
    assert(Spidola.hp == 50)
    Spidola.takeAction("Gara Cēlums", Spidola)
    assert(Spidola.hp == 60)
    //basic physattack
    Spidola.takeAction("Saules Dūriens", BulkyEnemy)
    assert(BulkyEnemy.hp == 431)
    //locked, revive does not work
    Lacplesis.isAlive = false
    Spidola.takeAction("Atmostas Varoņi", Lacplesis)
    assert(!Lacplesis.isAlive)
  }

  test("stats and battle options after proper levelup"){
    class Bulky extends Character(false, 500, 500, 10, 50, 10, 50, 30, 30)
    val BulkyEnemy: Character = new Bulky()
    val Lacplesis: Character = new Lacplesis()
    val Spidola: Character = new Spidola()

    Lacplesis.exp = 1500
    Lacplesis.lvlup()
    Spidola.exp = 1500
    Spidola.lvlup()

    //stats for Lacplesis
    assert(Lacplesis.isAlly)
    assert(Lacplesis.maxhp == 175)
    assert(Lacplesis.hp == 175)
    assert(Lacplesis.attk == 75)
    assert(Lacplesis.deff == 70)
    assert(Lacplesis.magattk == 0)
    assert(Lacplesis.magdeff == 25)
    assert(Lacplesis.maxmana == 15)
    assert(Lacplesis.mana == 15)

    //stats for Spidola
    assert(Spidola.isAlly)
    assert(Spidola.maxhp == 90)
    assert(Spidola.hp == 90)
    assert(Spidola.attk == 20)
    assert(Spidola.deff == 40)
    assert(Spidola.magattk == 95)
    assert(Spidola.magdeff == 125)
    assert(Spidola.maxmana == 175)
    assert(Spidola.mana == 175)

    //new attacks for Lacplesis
    //attack that negates enemy defense stat
    Lacplesis.takeAction("Lāča Dūre", BulkyEnemy)
    assert(BulkyEnemy.hp == 475)
    //a strong attack, but uses mana
    Lacplesis.takeAction("Lāča Spēks", BulkyEnemy)
    assert(BulkyEnemy.hp == 175)

    //revive ability for Spidola
    //locked, revive does not work
    Lacplesis.isAlive = false
    Spidola.takeAction("Atmostas Varoņi", Lacplesis)
    assert(Lacplesis.isAlive)

  }
}
