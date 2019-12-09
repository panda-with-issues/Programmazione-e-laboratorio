;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esercitazione_1) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
;; task 1

(define match
  (lambda (u v)
    (if (or (string=? u "") (string=? v ""))
        ""
        (let ((uh (string-ref u 0)) (vh (string-ref v 0))
              (s (match (substring u 1) (substring v 1)))
              )
          (if (char=? uh vh)
              (string-append (string uh) s)
              (string-append "*" s)
              ))
        )))

;; test

(match "astrazione" "estremi") ; "*str**i"

;; task 2

(define offset (char->integer #\0))

(define last-digit
  (lambda (base) (integer->char (+ (- base 1) offset)) ))

(define next-digit
  (lambda (dgt) (string (integer->char (+ (char->integer dgt) 1))) ))

(define increment
  (lambda (num base) ; 2 <= base <= 10
    (let ((digits (string-length num)))
      (if (= digits 0)
          "1"
          (let ((dgt (string-ref num (sub1 digits))))
            (if (char=? dgt (last-digit base))
                (string-append (increment (substring num 0 (sub1 digits)) base)
                               "0")
                (string-append (substring num 0 (- digits 1)) (next-digit dgt))
                ))
          ))))

;; test

(increment "1011" 2) ; "1100"

;; task 3

(define lcs      ; valore: lista di terne
  (lambda (u v)  ; u, v: stringhe
    (lcs-rec 1 u 1 v)
    ))

(define lcs-rec
  (lambda (i u j v)
    (cond ((or (string=? u "") (string=? v ""))
           null
           )
          ((char=? (string-ref u 0) (string-ref v 0))
           (cons (list i j (substring u 0 1))
                 (lcs-rec (+ i 1) (substring u 1) (+ j 1) (substring v 1)) ))
          (else
           (better (lcs-rec (add1 i) (substring u 1) j v)
                   (lcs-rec i u (add1 j) (substring v 1))))
           )))

(define better
  (lambda (x y)
    (if (< (length x) (length y)) y x)
        ))

;; tests

(lcs "pino" "pino")  ; ((1 1 "p") (2 2 "i") (3 3 "n") (4 4 "o"))
(lcs "pelo" "peso")  ; ((1 1 "p") (2 2 "e") (4 4 "o"))
(lcs "ala" "palato") ; ((1 2 "a") (2 3 "l") (3 4 "a"))
(lcs "arto" "atrio") ; ((1 1 "a") (3 2 "t") (4 5 "o"))


              