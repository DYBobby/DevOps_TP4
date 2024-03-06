package datastruct;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyUnsortedListTest {
	
	private UnsortedList<Integer> listInteger;
	private UnsortedList<Integer> listIntegerNonVide;

	@Before
	public void init() {
		listInteger = MyUnsortedList.of();
		listIntegerNonVide = MyUnsortedList.of(25, 26, 27);
	}
	
	@Test
	public void testIsEmptySurListeVide() {
		assertTrue("La liste est vide", listInteger.isEmpty());
	}
	
	@Test
	public void testIsEmptySurListeAuDebutVide() {
		listInteger.append(1);
		assertFalse("La liste n'est vide", listInteger.isEmpty());
	}
	
	@Test
	public void testIsEmptySurListeNonVide() {
		assertFalse("La liste n'est pas vide", listIntegerNonVide.isEmpty());
	}
	
	@Test
	public void testSizeSurListeNonVide() {
		assertEquals("La liste est de taille 3", 3, listIntegerNonVide.size());
	}
	
	@Test
	public void testSizeApresAjoutElements() {
		assertEquals("Au debut la liste est vide", 0, listInteger.size());
		listInteger.append(1);
		listInteger.append(2);
		assertEquals("La liste est de taille 2", 2, listInteger.size());
	}
	
	@Test
	public void testSizeApresSuppressionElements() {
		assertEquals("Au debut la liste est vide", 0, listInteger.size());
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		listInteger.pop();
		listInteger.popLast();	// bug: manquait un "size--" dans popLast()
		assertEquals("La liste est de taille 1", 1, listInteger.size());
		
	}
	
	@Test
	public void testUnAppend() {
		listInteger.append(1);
		int elem = listInteger.pop();		
		assertEquals("Le premier element doit etre 1", 1, elem);
	}
	
	@Test
	public void testPlusieursAppend() {
		listInteger.append(1);
		listInteger.append(2);
		int last = listInteger.popLast();		
		assertEquals("Le premier element doit etre 1", 2, last);
	}
	
	@Test
	public void testUnPrepend() {
		listInteger.prepend(25);
		int elem = listInteger.pop();
		assertEquals("Le premier element doit etre 1", 25, elem);
	}
	
	@Test
	public void testPlusieursPrepend() {
		listInteger.prepend(25);
		listInteger.prepend(26);
		int first = listInteger.pop();
		assertEquals("Le premier element doit etre 26", 26, first);
	}
	
	@Test
	public void testInsertDebut() {
		listInteger.insert(123, 0);
		int elem = listInteger.pop();
		assertEquals("Le premier element doit etre 123", 123, elem);
		assertTrue("La liste devrait etre vide maintenant", listInteger.isEmpty());
	}
	
	@Test
	public void testInsertFin() {
		listInteger.append(1);
		listInteger.insert(2, listInteger.size());
		int elem = listInteger.popLast();
		assertEquals("Le dernier element doit etre 2", 2, elem);
		assertFalse("La liste ne devrait etre vide maintenant", listInteger.isEmpty());
	}
	
	@Test
	public void testInsertMilieu() {
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		listInteger.insert(123, 1);
		int elem = listInteger.remove(1);
		assertEquals("Le deuxieme element doit etre 123", 123, elem);
	}
	
	@Test
	public void testInsertTropLoin() {
		assertThrows(IndexOutOfBoundsException.class, () -> listInteger.insert(1, 1));
	}
	
	@Test
	public void testInsertPositionNegative() {
		assertThrows(IndexOutOfBoundsException.class, () -> listInteger.insert(1, -1));
	}
	
	@Test
	public void testPopSurListeVide() {
		assertThrows(EmptyListException.class, () -> listInteger.pop());
	}
	
	@Test
	public void testPopLastSurListeVide() {
		assertThrows(IndexOutOfBoundsException.class, () -> listInteger.popLast());
	}
	
	@Test
	public void testRemoveSurListeVide() {
		assertThrows(IndexOutOfBoundsException.class, () -> listInteger.remove(1));
	}
	
	@Test
	public void testRemovePremierElement() {
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		int elem = listInteger.remove(0);
		assertEquals("Le premier element devrait etre 1", 1, elem);
	}
	
	@Test
	public void testRemoveDernierElement() {
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		int elem = listInteger.remove(listInteger.size() - 1);
		assertEquals("Le premier element devrait etre 3", 3, elem);
	}
	
	@Test 
	public void testRemoveElementMilieu() {
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		int elem = listInteger.remove(1);
		assertEquals("Le premier element devrait etre 2", 2, elem);
	}
	
	@Test
	public void testEqualsSurListesAvecMemesElementsOrdonnes() {
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		
		UnsortedList<Integer> listTMP = MyUnsortedList.of(1, 2, 3);
		assertTrue("Les deux listes sont les memes", listInteger.equals(listTMP));
	}
	
	@Test
	public void testEqualsSurListesAvecMemesElementsNonOrdonnes() {
		listInteger.append(1);
		listInteger.append(2);
		listInteger.append(3);
		
		UnsortedList<Integer> listTMP = MyUnsortedList.of(1, 3, 2);
		assertFalse("Les deux listes sont les memes", listInteger.equals(listTMP));
	}
	
	@Test
	public void testEqualsSurListesVides() {
		UnsortedList<Integer> listTMP = MyUnsortedList.of();
		assertTrue("Les deux listes sont les memes", listInteger.equals(listTMP));
	}
	
	@Test 
	public void testEqualsAvecListeNULL() {
		assertFalse("Une des deux listes est vide", listInteger.equals(null));
	}
	
	@Test
	public void testEqualsListesDiffTaille() {
		UnsortedList<Integer> listTMP = MyUnsortedList.of(1, 2, 3, 3);
		assertFalse("Les tailles des listes ne sont pas les memes", listInteger.equals(listTMP));
	}
	
	@Test
	public void testEqualsListesDifferentes() {
		listInteger.append(65);
		listInteger.append(66);
		UnsortedList<Double> listTMP = MyUnsortedList.of(65.0, 66.0);
		assertFalse("Les listes ne sont pas les memes", listInteger.equals(listTMP));
	}
	
}
