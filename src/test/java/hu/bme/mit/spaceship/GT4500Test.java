package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockStorePrimary;
  private TorpedoStore mockStoreSecondary;

  @BeforeEach
  public void init(){
    mockStorePrimary = mock(TorpedoStore.class);
    mockStoreSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockStorePrimary, mockStoreSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);
    verify(mockStoreSecondary, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);
    verify(mockStoreSecondary, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Primary_Fired_Twice(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(false);
    //Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(mockStorePrimary, times(2)).fire(1);
    verify(mockStoreSecondary, times(0)).fire(1);
    assertEquals(true, result1);
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_Single_Failure(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(mockStorePrimary, times(0)).fire(1);
    verify(mockStoreSecondary, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_Failure(){
    //Arrange
    when(mockStorePrimary.fire(1)).thenReturn(false);
    when(mockStoreSecondary.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_PrimaryEmpty_Success(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.fire(1)).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(mockStorePrimary, times(0)).fire(1);
    verify(mockStoreSecondary, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Alternating(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);
    //Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(mockStorePrimary, times(1)).fire(1);
    verify(mockStoreSecondary, times(1)).fire(1);
    assertEquals(true, result1);
    assertEquals(true, result2);
  }
}
