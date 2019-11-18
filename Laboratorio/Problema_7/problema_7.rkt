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

;; test
(position 7 '(7 8 24 35 41))   ; 0
(position 35 '(7 8 24 35 41))  ; 3
(position 41 '(7 8 24 35 41))  ; 4

(define sorted-ins  ; val: sorted list of integer including i and in which each number is repeated no more than once
  (lambda (i lst)   ; i: integer, lst: sorted list of integer in which each number is repeated no more than once
    ; base cases
    (cond ((null? lst)
           (cons i null)
           )
          ((= i (car lst))
           lst
           )
          ((< i (car lst))
           (cons i lst)
           )
          ; recursive step
          (else
           (cons (car lst) (sorted-ins i (cdr lst)))
           )
          )
    ))

;; test
(sorted-ins 24 '())             ; '(24)
(sorted-ins 5 '(7 8 24 35 41))  ; '(5 7 8 24 35 41)
(sorted-ins 24 '(7 8 24 35 41)) ; '(7 8 24 35 41)
(sorted-ins 27 '(7 8 24 35 41)) ; '(7 8 24 27 35 41)