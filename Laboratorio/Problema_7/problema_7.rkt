;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_7) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define belong?    ; val: bool, check whether an item is in a given list
  (lambda (i lst)  ; i: integer, lst: list of sorted integers
    ; base cases
    (cond ((null? lst)
           #f
           )
          ((= (car lst) i)
           #t
           )
          ; recursive step
          (else
           (belong? i (cdr lst))
           )
      )
    ))

;; test
(belong? 18 '())             ; #false
(belong? 18 '(5 7 10 18 23)) ; #true
(belong? 18 '(5 7 10 12 23)) ; #false

(define position   ; val: non-negative integer, index of an item in a given list
  (lambda (i lst)  ; i: integer, lst: sorted list of integer in which each number is repeated no more than once
    ; base case
    (if (= (car lst) i)
        0
        ; recursive step
        (add1 (position i (cdr lst)))
        )
    ))
