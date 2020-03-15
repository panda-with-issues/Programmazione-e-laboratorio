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

(define H
  (lambda (f g)
    (lambda (m n)
      (if (= n 0)
          (f m)
          (g m (H f (lambda (n) (- n 1))))
          )
    )))

(define s2
  (lambda (u v)
    (+ 1 v)
    ))

(define add (H (lambda (m) m) s2))

; test
(add 3 2)