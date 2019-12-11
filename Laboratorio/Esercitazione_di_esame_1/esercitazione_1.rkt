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

;; task 4

(define cyclic-string       ; val: string
  (lambda (pattern length)  ; pattern: string, length: non-negative integer
    (if (zero? length)
        (substring pattern 0 1)
        (string-append (cyclic-string pattern (sub1 length))
                       (string (string-ref pattern (remainder length (string-length pattern))))
                       )
        )
    ))

;; tests

(cyclic-string "abcd" 0)  ; ""
(cyclic-string "abcd" 1)  ; "a"
(cyclic-string "abcd" 2)  ; "ab"
(cyclic-string "abcd" 4)  ; "abcd"
(cyclic-string "abcd" 5)  ; "abcda"
(cyclic-string "abcd" 11) ; "abcdabcdabc"

;; task 5

(define av       ; val: list 
  (lambda (lst)  ; list composed by elements in {-1, 0, 1}
    (if (= (length lst) 1)
        null
        (let ((first (car lst))
              (second (car (cdr lst)))
              )
          (let ((sum (+ first second)))
            (cond
              ((negative? sum)
               (cons -1 (av (cdr lst)))
               )
              ((zero? sum)
               (cons 0 (av (cdr lst)))
               )
              (else
               (cons 1 (av (cdr lst)))
               )
              )
            )
          )
        )
    ))

;; test

(av '(0 0 -1 -1 1 0 0 1 0)) ; (0 -1 -1 0 1 0 1 1)

;; task 6

(define r-val
  (lambda (numeral)
    (if (string=? numeral ".")
        0
        (let ((last-idx (sub1 (string-length numeral))))
          (+ (* (string->number (substring numeral last-idx)) (expt 2 (- last-idx)))
             (r-val (substring numeral 0 last-idx)))
          )
        )
    ))

;; test

(r-val ".1")   ; 0.5
(r-val ".011") ; 0.375

;; task 7

(define shared
  (lambda (u v)
    (cond
      ((or (null? u) (null? v))
        null
        )
      ((= (car u) (car v))
       (cons (car u) (shared (cdr u) (cdr v)))
       )
      ((< (car u) (car v))
       (shared (cdr u) v)
       )
      (else
       (shared u (cdr v))
       )
      )
    ))

;; test

(shared '(1 3 5 6 7 8 9 10) '(0 1 2 3 4 5 7 9)) ; (1 3 5 7 9)

;; task 8

(define 1-counter
  (lambda (str)
    (if (zero? (string-length str))
        0
        (+ (string->number (substring str 0 1))
           (1-counter (substring str 1))
           )
        )
    ))

(define parity-check-rec
  (lambda (lst i)
    (cond
      ((null? lst)
       null
       )
      ((odd? (1-counter (car lst)))
       (cons i (parity-check-rec (cdr lst) (add1 i)))
       )
      (else
       (parity-check-rec (cdr lst) (add1 i))
       )
      )
    ))

(define parity-check-failures
  (lambda (lst)
    (parity-check-rec lst 0)
    ))

;; test

(parity-check-failures '("0111" "1001" "0000" "1010")) ; '(0)
(parity-check-failures '("0110" "1101" "0000" "1011")) ; '(1 3)
(parity-check-failures '("0111" "1011" "0100" "1110")) ; '(0 1 2 3)
(parity-check-failures '("0110" "1001" "0000" "1010")) ; '()

;; task 9

(define find-min-diff
  (lambda (couple lst)
    (if (= 1 (length lst))
        couple
        (let ((a (car couple))
              (b (car (cdr couple)))
              (c (car lst))
              (d (car (cdr lst)))
              )
          (let ((current-min (- b a))
                (diff (- d c))
                )
            (if (<= current-min diff)
                (find-min-diff couple (cdr lst))
                (find-min-diff (list c d) (cdr lst))
                )
            )
          )
        )
    ))

(define closest-pair
  (lambda (lst)
    (let ((a (car lst))
          (b (car (cdr lst)))
          )
      (find-min-diff (list a b) lst)
      )
    ))

;; test

(closest-pair '(0.1 0.3 0.5 0.6 0.8 1.1)) ; (0.5 0.6)