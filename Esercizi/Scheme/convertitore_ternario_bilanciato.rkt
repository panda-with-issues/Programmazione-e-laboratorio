;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname convertitore_ternario_bilanciato) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define digit-btr->rad10 ; val: cifra tra [-1;1]
  (lambda (btr-d)        ; btr-d: carattere tra [+, -, .]
    (cond ((string=? btr-d "+") 1)
          ((string=? btr-d "-") -1)
          (else 0)
      )
    ))

(define btr->rad10  ; val: numero in base 10
  (lambda (str)     ; str: stringa di [+, -, .]
    (let ((last-d-idx (- (string-length str) 1)))
      (let ((last-d (substring str last-d-idx))
            (left-str (substring str 0 last-d-idx))
            )
        ; caso base
        (if (<= (string-length str) 1)
            (digit-btr->rad10 str)
            (+ (* (btr->rad10 left-str) 3) (digit-btr->rad10 last-d))
            )
    ))))

(define remainder->btr  ; val: stringa con un carattere tra [+, -, .]
  (lambda (r)           ; r: numero intero tra [0;2]
    (cond ((= r 2)  "+-")
          ((= r 1)  "+")
          ((= r -1) "-")
          ((= r -2) "-+")
          (else     ".")
      )
    ))

(define rad10->btr  ; val: stringa di cifre +, -, .
  (lambda (n)       ; n: numero intero in base 10
    (let ((quot (quotient n 3))
          (rem (remainder n 3)))
    ; caso base
    (if (and (< -3 n) (< n 3))
        (remainder->btr rem)
        ; passo ricorsivo
        (string-append
         (cond ((= rem 2)  (rad10->btr (+ quot 1)))
               ((= rem -2) (rad10->btr (- quot 1)))
               (else       (rad10->btr quot))
               )
         (cond ((= rem 2)  "-")
               ((= rem -2) "+")
               (else       (remainder->btr rem))
               )
        )
        )
    )))
 (rad10->btr -4592)
 (btr->rad10 "-+.-.+.-+")
