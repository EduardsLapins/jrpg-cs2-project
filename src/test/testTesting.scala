import org.scalatest._
import Player.Character

class testTesting extends FunSuite{
  test("a simple test of all current Character functions") {
    val Boi: Character = new Character(45,10, 20, 30, 90, 39, 30, 4)
    assert(Boi.isAlive == true)
    Boi.takeDamage(5)
    assert(Boi.isAlive == true)
    assert(Boi.hp == 5)
    Boi.takeDamage(5)
    assert(Boi.hp == 0)
    assert(Boi.isAlive == false)
  }
}
