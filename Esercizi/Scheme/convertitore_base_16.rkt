;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname convertitore_base_16) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define cifra16 ; val: stringa esadecimale di una cifra
  (lambda (n)   ; n: numero decimale minore di 16
    (cond ((= n 10) "A")
          ((= n 11) "B")
          ((= n 12) "C")
          ((= n 13) "D")
          ((= n 14) "E")
          ((= n 15) "F")
          (else (number->string n))
          )
    ))

(define base10->16  ; val: stringa di cifre in base 16
  (lambda (n)       ; n: numero in base 10 non negativo
    (let ((resto (remainder n 16))
          (quoz (quotient n 16))
          )
      ; caso base
      (if (zero? quoz)
          (cifra16 resto)
          ; passo induttivo
          (string-append
           ; passo ricorsivo
           (base10->16 quoz)
           (cifra16 resto)
           )
          )
      )
    ))

;; test
(base10->16 0)
(base10->16 544)    ; 220
(base10->16 543962) ; 84CDA 
(base10->16 382433) ; 5D5E1
(base10->16 984450) ; F0582