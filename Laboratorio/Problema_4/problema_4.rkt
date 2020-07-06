;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_4) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define lsd      ; val:string composed by one char in [+, -, .]
  (lambda (str)  ; str: string
    (if (string=? str "")
        #\.
        (string-ref str (- (string-length str) 1))
        )
    ))

(define head     ; val: string composed by btr digits before the lsd [+, -, .]
  (lambda (str)  ; str: string
    (if (string=? str "")
        str
        (substring str 0 (- (string-length str) 1))
        )
    ))

(define normalized-btr  ; val: a btr representation stripped of any initial #\. char
  (lambda (btr)         ; btr: string composed by chars in [+, ., -]
    ; base cases
    (if (or (string=? btr ".")
            (not (char=? (string-ref btr 0) #\.))
            )
        btr
        ; recursive step
        (normalized-btr (substring btr 1))
        )
    ))

(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

(define btr-carry                        ; val: one char in [+, -, .]
                                         ; it represents the possible sum's carry of first and second lsds
  (lambda (u v c)                        ; u, v, c: one char in [+, -, .]
                                         ; u, v are the two lsds, c is the possible carry
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\-)
                        ((char=? c #\.)  ; - - .
                         #\-)
                        ((char=? c #\+)  ; - - +
                         #\.)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\-)
                        ((char=? c #\.)  ; - . .
                         #\.)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  #\.)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\-)
                        ((char=? c #\.)  ; . - .
                         #\.)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  #\.)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\.)
                        ((char=? c #\+)  ; . + +
                         #\+)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  #\.)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\.)
                        ((char=? c #\+)  ; + . +
                         #\+)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\.)
                        ((char=? c #\.)  ; + + .
                         #\+)
                        ((char=? c #\+)  ; + + +
                         #\+)))))
          )))

(define btr-carry-sum    ; val: string, sum's btr representention of two nums with possible carries
  (lambda (add1 add2 c)  ; add1, add2: strings, btr representations of nums
                         ; c: char, possible in-going carry
    (let ((a (lsd add1))
          (b (lsd add2))
          (a_head (head add1))
          (b_head (head add2))
          )
      ; base case
      (if (and
           (char=? a #\.)
           (char=? b #\.)
           )
          (string c)
          ; recursive step
          (string-append
           (btr-carry-sum
            a_head
            b_head
            (btr-carry a b c)
            )
           (string (btr-digit-sum a b c))
           )
          )
      )))

(define btr-sum        ; val: string composed by chars in [+, -, .]
  (lambda (add1 add2)  ; add1, add2: strings composed by chars in [+, -, .]
    (normalized-btr (btr-carry-sum add1 add2 #\.))
    ))

;; test

(btr-sum "-+--" "+")     ; "-+-."
(btr-sum "-+--" "-")     ; "-.++"
(btr-sum "+-.+" "-+.-")  ; "."
(btr-sum "-+--+" "-.--") ; "--++."
(btr-sum "-+-+." "-.-+") ; "-.-.+"
(btr-sum "+-+-." "+.+-") ; "+.+.-"
