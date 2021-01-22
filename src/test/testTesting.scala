import org.scalatest._
import Player.Character

class testTesting extends FunSuite{
  test("a simple test of all takeDamage functions") {
    val Boi: Character = new Character(45,10, 20, 30, 90, 39, 30, 4)

    //laimīgs dzīvo
    assert(Boi.isAlive)

    //uzkrīt lāsteka ->  >;(
    Boi.takeDamage(5)
    assert(Boi.isAlive)
    assert(Boi.hp == 5)

    //uzkrīt flīģelis -> x_x
    Boi.takeDamage(5)
    assert(Boi.hp == 0)
    assert(Boi.isAlive == false)
  }

  test("testing physAttack") {
    val Sitejs: Character = new Character(45,10, 20, 30, 10, 39, 30, 30)
    val Mizejs: Character = new Character(20,20, 10, 50, 10, 15, 30, 4)

    //kaujas
    assert(Mizejs.isAlive)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == 12)
    assert(Mizejs.isAlive)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == 4)

    //beidzot paruba
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == -4)
    assert(Mizejs.isAlive == false)
  }

  test("testing magAttack") {
    val Sitejs: Character = new Character(45,10, 20, 30, 6, 39, 32, 31)
    val Mizejs: Character = new Character(20,20, 10, 50, 10, 19, 30, 4)

    //baigi kaujas
    assert(Mizejs.isAlive)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 18)
    assert(Mizejs.isAlive)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 16)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 14)
    assert(Mizejs.isAlive)
    assert(Sitejs.mana == 16)

    //turpina šaut pupiņas
    Sitejs.magAttack(Mizejs)
    Sitejs.magAttack(Mizejs)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 8)
    assert(Mizejs.isAlive)
    assert(Sitejs.mana == 1)

    //šauj vēl mīkstākas pupiņas
    Sitejs.magAttack(Mizejs)
    assert(Sitejs.mana == 0)
    assert(Mizejs.isAlive)
    assert(Mizejs.hp == 7)

    //vēlreiz pakutina, bet nesanāk
    Sitejs.magAttack(Mizejs)
    assert(Sitejs.mana == 0)
    assert(Mizejs.isAlive)
    assert(Mizejs.hp == 7)
  }
}
