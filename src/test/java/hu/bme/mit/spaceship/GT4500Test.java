package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.ship = mock(GT4500.class);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(ship.fireTorpedo(FiringMode.SINGLE)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(ship, times(1)).fireTorpedo(FiringMode.SINGLE);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(ship.fireTorpedo(FiringMode.ALL)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(ship, times(1)).fireTorpedo(FiringMode.ALL);
    assertEquals(true, result);
  }

}
