# CompilatoreAcDC
Compilatore scritto in Java che consente di tradurre programmi Ac in codice Dc (notazione polacca inversa)

# Example

Source Program

  \\ int tempa;
  tempa = 5; 
  float tempb = tempa + 3.2;
  tempb = tempb + 7;
  print tempb;

DC Code

  5 sa 0 k
  la 5 k 3.2 + sb 0 k
  lb 7 5 k + sb 0 k
  lb p P

