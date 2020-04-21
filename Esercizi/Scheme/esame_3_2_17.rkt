;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esame_3_2_17) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
 (define f        ; intero
   (lambda (s)    ; lista di interi positivi
     (g s 0)
     ))

(define g
  (lambda (s b)
    (if (null? s)
        0
        (if (<= (car s) b)
            (g (cdr s) b)
            (max (g (cdr s) b)
                 (+ (g (cdr s) (car s)) 1))
            ))))

(f '(5))
(f '(5 3))
(f '(1 2))
(f '(5 3 1))
(f '(1 3 5))
(f '(1 5 3))
(f '(1 4 16))
(f '(4 2 3 1 5))

(define btr-rec
  (lambda (n str)
    (cond ((= n -1)
           (string-append str "-")
           )
          ((= n 0)
           (string-append str  ".")
           )
          ((= n 1)
           (string-append  str "+")
           )
          (else
           (let ((q (quotient n 3))
                 (r (remainder n 3))
                 )
             (cond ((= r -2)
                    (btr-rec (- q 1) (string-append  str "+"))
                    )
                   ((= r -1)
                    (btr-rec q (string-append  str "-"))
                    )
                   ((= r 0)
                    (btr-rec q (string-append  str "."))
                    )
                   ((= r 1)
                    (btr-rec q (string-append  str "+"))
                    )
                   (else
                    (btr-rec (+ q 1) (string-append  str "-"))
                    )
                   )
             )
           )
          )
    ))

(define btr-tail
  (lambda (n)
    (btr-rec n "")
    ))
