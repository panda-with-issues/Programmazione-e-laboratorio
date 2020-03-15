;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_9) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
 ;; Task 1

(define alphabet "ABCDEFGHILMNOPQRSTVX")

(define caesar-cipher  ; val: letter -> letter
  (lambda (key)        ; int in [0, 19]
    (lambda (char)     ; char in alphabet
      (let ((i (remainder (+ (index-of char alphabet) key) 20)))
        (string-ref alphabet i)
        )
    )))

(define index-of           ; val: int
  (lambda (char alphabet)  ; char: char, alphabet: string
    (if (char=? char (string-ref alphabet 0))
        0
        (+ 1 (index-of char (substring alphabet 1)))
        )
    ))

(define encrypt            ; this procedure is meant for debug only
                           ; val: char
  (lambda (char rule)      ; char: char, rule: letter->letter
    (rule char)
    ))

;; Task 2

(define i
 (lambda (m)
   m))

(define z
  (lambda (m)
    0
    ))

(define u
  (lambda (m)
    1
    ))

(define s2
  (lambda (u v)
    (+ 1 v)
    ))

(define H
  (lambda (f g)
    (lambda (m n)
      (if (= n 0)
          (f m)
          (g m
             ; (g m (add m (- n 1))
             ; ((lambda (h) (h m (- n 1))) (H f g))
             ((H f g) m (- n 1))
             )
          )
      )))

(define add (H i s2))
(define mul (H z add))
(define pow (H u mul))

; test
(add 0 0) ; 0
(add 3 0) ; 3
(add 1 5) ; 6
(mul 0 5) ; 0
(mul 1 5) ; 5
(mul 5 4) ; 20
(pow 2 0) ; 1
(pow 2 1) ; 2
(pow 2 5) ; 32